package com.zdf.taskmanager.dto;

public class Document {
    private String id;
    private String size;
    private String documentName;
    private String documentType;
    private byte[] content;

    public Document(String id, String size, String documentName, String documentType, byte[] content) {
        this.id = id;
        this.size = size;
        this.documentName = documentName;
        this.documentType = documentType;
        this.content = content;
    }

    public Document() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
