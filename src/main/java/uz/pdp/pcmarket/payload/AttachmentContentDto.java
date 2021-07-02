package uz.pdp.pcmarket.payload;

import lombok.Data;
import uz.pdp.pcmarket.entity.Attachment;

import javax.persistence.OneToOne;

@Data
public class AttachmentContentDto {

    private byte[] bytes;
    private Integer attachmentId;

}
