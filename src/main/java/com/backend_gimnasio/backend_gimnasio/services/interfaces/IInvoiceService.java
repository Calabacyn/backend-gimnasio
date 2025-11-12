package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.InvoiceDTO;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    List<InvoiceDTO> getAll();
    Optional<InvoiceDTO> getBy(Long id);
    void create(InvoiceDTO invoiceDTO);
    void update(InvoiceDTO invoiceDTO);
    void delete(Long id);
}