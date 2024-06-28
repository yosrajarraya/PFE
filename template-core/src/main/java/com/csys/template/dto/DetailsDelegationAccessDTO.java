package com.csys.template.dto;

import java.lang.Integer;
import javax.validation.constraints.NotNull;

public class DetailsDelegationAccessDTO {

    private Integer numDelegation;
    private String userDelegataire;
    private String userDelegant;

    public Integer getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }



    public String getUserDelegataire() {
        return userDelegataire;
    }

    public void setUserDelegataire(String userDelegataire) {
        this.userDelegataire = userDelegataire;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }


}
