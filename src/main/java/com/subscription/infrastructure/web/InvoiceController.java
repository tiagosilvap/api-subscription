package com.subscription.infrastructure.web;

import com.subscription.application.invoice.IssueInvoiceResponse;
import com.subscription.application.invoice.IssueInvoiceUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final IssueInvoiceUseCase issueInvoiceUseCase;

    public InvoiceController(IssueInvoiceUseCase issueInvoiceUseCase) {
        this.issueInvoiceUseCase = issueInvoiceUseCase;
    }

    @PostMapping("/{id}/invoices")
    public ResponseEntity<IssueInvoiceResponse> invoice(
            @PathVariable UUID id,
            @RequestParam("dueDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dueDate) {
        return ResponseEntity.ok(issueInvoiceUseCase.handle(id, dueDate));
    }
}

