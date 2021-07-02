package uz.pdp.pcmarket.payload;

import lombok.Data;

@Data
public class AttachmentDto {
    private String name;

    private String contentType;

    private long size;

}
