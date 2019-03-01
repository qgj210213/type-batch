package com.type;

import org.springframework.boot.SpringApplication;

import com.type.jobtogether.BatchJobArticleImport;

public class Main {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(BatchJobArticleImport.class, args)));
    }

}
