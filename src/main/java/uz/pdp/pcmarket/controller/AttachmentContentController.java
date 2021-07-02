package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.AttachmentContent;
import uz.pdp.pcmarket.payload.AddressDto;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentContentDto;
import uz.pdp.pcmarket.repository.AttachmentContentRepository;
import uz.pdp.pcmarket.service.AddressService;
import uz.pdp.pcmarket.service.AttachmentContentService;

import java.util.List;

@RestController
@RequestMapping("/api/attachmentContent")
public class AttachmentContentController {

    @Autowired
    AttachmentContentService attachmentContentService;

    @GetMapping
    public HttpEntity<?> getAttachmentContents() {
        List<AttachmentContent> attachmentContents = attachmentContentService.getAttachmentContents();
        return ResponseEntity.ok(attachmentContents);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAttachmentContent(@PathVariable Integer id) {
        AttachmentContent attachmentContent = attachmentContentService.getAttachmentContent(id);
        return ResponseEntity.ok(attachmentContent);
    }

    @PostMapping
    public HttpEntity<?> addAttachmentContent(@RequestBody AttachmentContentDto attachmentContentDto) {
        ApiResponse apiResponse = attachmentContentService.addAttachmentContent(attachmentContentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAttachmentContent(@PathVariable Integer id,@RequestBody AttachmentContentDto attachmentContentDto){
        ApiResponse apiResponse = attachmentContentService.editAttachmentContent(id, attachmentContentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachmentContent(@PathVariable Integer id){
        ApiResponse apiResponse = attachmentContentService.deleteAttachmentContent(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:409).body(apiResponse);
    }
}
