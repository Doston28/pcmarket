package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.payload.AddressDto;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentDto;
import uz.pdp.pcmarket.repository.AddressRepository;
import uz.pdp.pcmarket.repository.AttachmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Attachment> getAttachments() {
        return attachmentRepository.findAll();
    }

    public Attachment getAttachment(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElseGet(Attachment::new);
    }

    public ApiResponse addAttachment(AttachmentDto attachmentDto) {
        Attachment attachment = new Attachment();
        attachment.setName(attachmentDto.getName());
        attachment.setSize(attachmentDto.getSize());
        attachment.setContentType(attachmentDto.getContentType());
        attachmentRepository.save(attachment);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editAttachment(Integer id, AttachmentDto attachmentDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Attachment not found", false);
        Attachment attachment = optionalAttachment.get();
        attachment.setName(attachmentDto.getName());
        attachment.setSize(attachmentDto.getSize());
        attachment.setContentType(attachmentDto.getContentType());
        attachmentRepository.save(attachment);
        return new ApiResponse("Attachment edited", true);
    }

    public ApiResponse deleteAttachment(Integer id) {
        try {
            attachmentRepository.deleteById(id);
            return new ApiResponse("Attachment deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
