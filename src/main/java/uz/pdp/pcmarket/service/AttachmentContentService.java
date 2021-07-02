package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pcmarket.entity.Address;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.AttachmentContent;
import uz.pdp.pcmarket.payload.AddressDto;
import uz.pdp.pcmarket.payload.ApiResponse;
import uz.pdp.pcmarket.payload.AttachmentContentDto;
import uz.pdp.pcmarket.repository.AttachmentContentRepository;
import uz.pdp.pcmarket.repository.AttachmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentContentService {

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public List<AttachmentContent> getAttachmentContents() {
        return attachmentContentRepository.findAll();
    }

    public AttachmentContent getAttachmentContent(Integer id) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        return optionalAttachmentContent.orElseGet(AttachmentContent::new);
    }

    public ApiResponse addAttachmentContent(AttachmentContentDto attachmentContentDto) {
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(attachmentContentDto.getBytes());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentContentDto.getAttachmentId());
       if (!optionalAttachment.isPresent())
           return new ApiResponse("Attachment not found",false);
        attachmentContent.setAttachment(optionalAttachment.get());
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("Ok", true);
    }

    public ApiResponse editAttachmentContent(Integer id, AttachmentContentDto attachmentContentDto) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        if (!optionalAttachmentContent.isPresent())
            return new ApiResponse("AttachmentContent not found", false);
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContent.setBytes(attachmentContentDto.getBytes());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentContentDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Attachment not found",false);
        attachmentContent.setAttachment(optionalAttachment.get());
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("AttachmentContent edited", true);
    }

    public ApiResponse deleteAttachmentContent(Integer id) {
        try {
            attachmentContentRepository.deleteById(id);
            return new ApiResponse("AttachmentContent deleted", true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

}
