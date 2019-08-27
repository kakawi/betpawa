package com.hlebon.betpawa.api.request.user;

public final class CreateUserRequest {

    private final int id;
    private final String name;

    private CreateUserRequest(final Builder builder) {
        if (builder.id == null) {
            throw new IllegalArgumentException("Id is mandatory");
        }
        if (builder.name == null) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        this.id = builder.id;
        this.name = builder.name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final static class Builder {
        private Integer id;
        private String name;

        private Builder() {
        }

        public Builder setId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public CreateUserRequest build() {
            return new CreateUserRequest(this);
        }
    }
}
