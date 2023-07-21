package com.example.ssaesak.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicySmartResponseDTO  implements Serializable {

    private int policySmartId;

    private String title;

    private String applyContent;

    private String type;

    private String outline;

    private String mainContent;

    public PolicySmartResponseDTO() {
    }

    public PolicySmartResponseDTO(int policySmartId, String title, String applyContent, String type, String outline, String mainContent) {
        this.policySmartId = policySmartId;
        this.title = title;
        this.applyContent = applyContent;
        this.type = type;
        this.outline = outline;
        this.mainContent = mainContent;
    }

    public int getPolicySmartId() {
        return policySmartId;
    }

    public void setPolicySmartId(int policySmartId) {
        this.policySmartId = policySmartId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }
}
