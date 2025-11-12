package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.exceptions.*;
import com.backend_gimnasio.backend_gimnasio.model.dtos.InvoiceDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.*;
import com.backend_gimnasio.backend_gimnasio.model.mappers.InvoiceMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.*;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IInvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MembershipRepository membershipRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              ClientRepository clientRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository,
                              MembershipRepository membershipRepository,
                              InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.membershipRepository = membershipRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<InvoiceDTO> getBy(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDTO);
    }

    @Override
    @Transactional
    public void create(InvoiceDTO invoiceDTO) {
        if (invoiceDTO.getId() != null) {
            throw new IllegalArgumentException("ID must be null for creation");
        }

        if (invoiceRepository.existsByNumber(invoiceDTO.getNumber())) {
            throw new RuntimeException("Invoice number already exists: " + invoiceDTO.getNumber());
        }

        Client client = null;
        if (invoiceDTO.getClientEmail() != null) {
            client = clientRepository.findById(invoiceDTO.getClientEmail())
                    .orElseThrow(() -> new ClientNotFoundException(invoiceDTO.getClientEmail()));
        }

        UserEntity registeredBy = userRepository.findById(invoiceDTO.getRegisteredByEmail())
                .orElseThrow(() -> new UserNotFoundException(invoiceDTO.getRegisteredByEmail()));

        List<InvoiceProductItem> productItems = invoiceDTO.getProductItems() != null ?
                invoiceDTO.getProductItems().stream()
                        .map(itemDTO -> {
                            Product product = productRepository.findById(itemDTO.getProductId())
                                    .orElseThrow(() -> new ProductNotFoundException(itemDTO.getProductId()));
                            return InvoiceProductItem.builder()
                                    .product(product)
                                    .quantity(itemDTO.getQuantity())
                                    .unitPrice(itemDTO.getUnitPrice())
                                    .subtotal(itemDTO.getSubtotal())
                                    .build();
                        }).toList() : List.of();

        List<InvoiceMembershipItem> membershipItems = invoiceDTO.getMembershipItems() != null ?
                invoiceDTO.getMembershipItems().stream()
                        .map(itemDTO -> {
                            Membership membership = membershipRepository.findById(itemDTO.getMembershipId())
                                    .orElseThrow(() -> new MembershipNotFoundException(itemDTO.getMembershipId()));
                            LocalDate expirationDate = itemDTO.getExpirationDate() != null ? itemDTO.getExpirationDate()
                                    : LocalDate.now().plusMonths(membership.getDurationMonths() != null ? membership.getDurationMonths() : 0);
                            Client itemClient;
                            if (itemDTO.getClientEmail() != null) {
                                itemClient = clientRepository.findById(itemDTO.getClientEmail())
                                        .orElseThrow(() -> new ClientNotFoundException(itemDTO.getClientEmail()));
                            } else if (invoiceDTO.getClientEmail() != null) {
                                itemClient = clientRepository.findById(invoiceDTO.getClientEmail())
                                        .orElseThrow(() -> new ClientNotFoundException(invoiceDTO.getClientEmail()));
                            } else {
                                itemClient = null;
                            }

                            return InvoiceMembershipItem.builder()
                                    .membership(membership)
                                    .client(itemClient)
                                    .price(itemDTO.getPrice())
                                    .expirationDate(expirationDate)
                                    .quantity(itemDTO.getQuantity())
                                    .build();
                        }).toList() : List.of();

        BigDecimal calculatedTotal = calculateTotal(productItems, membershipItems);

        Invoice invoice = invoiceMapper.toEntity(invoiceDTO, client, registeredBy, productItems, membershipItems);
        invoice.setTotal(calculatedTotal);
        invoiceRepository.save(invoice);
    }

    @Override
    @Transactional
    public void update(InvoiceDTO invoiceDTO) {
        Long id = Optional.ofNullable(invoiceDTO.getId())
                .orElseThrow(InvoiceNotFoundException::new);

        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException(id));

        if (!existingInvoice.getNumber().equals(invoiceDTO.getNumber()) &&
                invoiceRepository.existsByNumber(invoiceDTO.getNumber())) {
            throw new RuntimeException("Invoice number already exists: " + invoiceDTO.getNumber());
        }

        Client client = null;
        if (invoiceDTO.getClientEmail() != null) {
            client = clientRepository.findById(invoiceDTO.getClientEmail())
                    .orElseThrow(() -> new ClientNotFoundException(invoiceDTO.getClientEmail()));
        }

        UserEntity registeredBy = userRepository.findById(invoiceDTO.getRegisteredByEmail())
                .orElseThrow(() -> new UserNotFoundException(invoiceDTO.getRegisteredByEmail()));

        List<InvoiceProductItem> productItems = invoiceDTO.getProductItems() != null ?
                invoiceDTO.getProductItems().stream()
                        .map(itemDTO -> {
                            Product product = productRepository.findById(itemDTO.getProductId())
                                    .orElseThrow(() -> new ProductNotFoundException(itemDTO.getProductId()));
                            return InvoiceProductItem.builder()
                                    .product(product)
                                    .quantity(itemDTO.getQuantity())
                                    .unitPrice(itemDTO.getUnitPrice())
                                    .subtotal(itemDTO.getSubtotal())
                                    .build();
                        }).toList() : List.of();

        List<InvoiceMembershipItem> membershipItems = invoiceDTO.getMembershipItems() != null ?
                invoiceDTO.getMembershipItems().stream()
                        .map(itemDTO -> {
                            Membership membership = membershipRepository.findById(itemDTO.getMembershipId())
                                    .orElseThrow(() -> new MembershipNotFoundException(itemDTO.getMembershipId()));
                            LocalDate expirationDate = itemDTO.getExpirationDate() != null ? itemDTO.getExpirationDate()
                                    : LocalDate.now().plusMonths(membership.getDurationMonths() != null ? membership.getDurationMonths() : 0);
                            Client itemClient;
                            if (itemDTO.getClientEmail() != null) {
                                itemClient = clientRepository.findById(itemDTO.getClientEmail())
                                        .orElseThrow(() -> new ClientNotFoundException(itemDTO.getClientEmail()));
                            } else if (invoiceDTO.getClientEmail() != null) {
                                itemClient = clientRepository.findById(invoiceDTO.getClientEmail())
                                        .orElseThrow(() -> new ClientNotFoundException(invoiceDTO.getClientEmail()));
                            } else {
                                itemClient = null;
                            }

                            return InvoiceMembershipItem.builder()
                                    .membership(membership)
                                    .client(itemClient)
                                    .price(itemDTO.getPrice())
                                    .expirationDate(expirationDate)
                                    .quantity(itemDTO.getQuantity())
                                    .build();
                        }).toList() : List.of();

        BigDecimal calculatedTotal = calculateTotal(productItems, membershipItems);

        Invoice updatedInvoice = invoiceMapper.toEntity(invoiceDTO, existingInvoice, client, registeredBy, productItems, membershipItems);
        updatedInvoice.setTotal(calculatedTotal);
        invoiceRepository.save(updatedInvoice);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException(id));

        invoiceRepository.delete(invoice);
    }


    private BigDecimal calculateTotal(List<InvoiceProductItem> productItems, List<InvoiceMembershipItem> membershipItems) {
        BigDecimal productTotal = productItems.stream()
                .map(InvoiceProductItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal membershipTotal = membershipItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return productTotal.add(membershipTotal);
    }
}
