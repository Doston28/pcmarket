package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.payload.AddressDto;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentDto;
import uz.pdp.pcmarket.service.AddressService;
import uz.pdp.pcmarket.service.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public HttpEntity<?> getAttachments() {
        List<Attachment> attachments = attachmentService.getAttachments();
        return ResponseEntity.ok(attachments);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAttachment(@PathVariable Integer id) {
        Attachment attachment = attachmentService.getAttachment(id);
        return ResponseEntity.ok(attachment);
    }

    @PostMapping
    public HttpEntity<?> addAttachment(@RequestBody AttachmentDto attachmentDto) {
        ApiResponse apiResponse = attachmentService.addAttachment(attachmentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAttachment(@PathVariable Integer id,@RequestBody AttachmentDto attachmentDto){
        ApiResponse apiResponse = attachmentService.editAttachment(id, attachmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachment(@PathVariable Integer id){
        ApiResponse apiResponse = attachmentService.deleteAttachment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
}
