package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.exceptions.InvoiceNotFoundException;
import com.backend_gimnasio.backend_gimnasio.model.dtos.InvoiceDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IInvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final IInvoiceService invoiceService;

    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDTO> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getById(@PathVariable Long id) {
        InvoiceDTO invoice = invoiceService.getBy(id)
                .orElseThrow(() -> new InvoiceNotFoundException(id));
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(InvoiceDTO.Create.class) InvoiceDTO invoiceDTO) {
        invoiceService.create(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Validated(InvoiceDTO.Update.class) InvoiceDTO invoiceDTO) {
        invoiceService.update(invoiceDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        invoiceService.delete(id);
    }
}
