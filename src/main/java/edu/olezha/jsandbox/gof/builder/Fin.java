package edu.olezha.jsandbox.gof.builder;

import lombok.Data;

@Data
public class Fin {

    public static void main(String[] args) {
        System.out.println(
                new Builder()
                        .author("Oleh")
                        .text("ok")
                        .build()
        );
    }

    private final String author;
    private final String text;

    private Fin(Builder builder) {
        author = builder.author;
        text = builder.text;
    }

    public static class Builder {

        private String author;
        private String text;

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Fin build() {
            return new Fin(this);
        }
    }
}
