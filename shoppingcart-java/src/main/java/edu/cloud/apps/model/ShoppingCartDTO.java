package edu.cloud.apps.model;

import javax.validation.constraints.NotNull;


public class ShoppingCartDTO {

    private Long id;

    @NotNull
    private Boolean finalized;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Boolean getFinalized() {
        return finalized;
    }

    public void setFinalized(final Boolean finalized) {
        this.finalized = finalized;
    }

}
