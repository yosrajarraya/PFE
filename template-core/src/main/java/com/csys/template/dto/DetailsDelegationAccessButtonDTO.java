package com.csys.template.dto;

public class DetailsDelegationAccessButtonDTO {

    private Integer idDelegationButton;
    private Integer module;
    private String userDelegant;
    private Integer button;
    private ButtonDTO idButton;
    private int numDelegation;

    public int getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(int numDelegation) {
        this.numDelegation = numDelegation;
    }

    public Integer getIdDelegationButton() {
        return idDelegationButton;
    }

    public void setIdDelegationButton(Integer idDelegationButton) {
        this.idDelegationButton = idDelegationButton;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public Integer getButton() {
        return button;
    }

    public void setButton(Integer button) {
        this.button = button;
    }

    public ButtonDTO getIdButton() {
        return idButton;
    }

    public void setIdButton(ButtonDTO idButton) {
        this.idButton = idButton;
    }

}
