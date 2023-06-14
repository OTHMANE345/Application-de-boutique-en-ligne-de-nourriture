
package com.food;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;


import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.nio.file.Path;



@Service
public class ProductService {
    private final productRepository productRepository;

    public ProductService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public product saveProductWithImage(product product, MultipartFile imageFile) throws IOException {
        // Save the product information to the database
        product savedProduct = productRepository.save(product);

        // Save the image to the images folder in the static directory
        byte[] imageBytes = imageFile.getBytes();
        UUID imageName = UUID.randomUUID();
        Path path = Paths.get("src/main/resources/static/images/" + imageName + ".png");
        Files.write(path, imageBytes);

        // Update the saved product with the image information
        savedProduct.setImage(imageName.toString());
        savedProduct = productRepository.save(savedProduct);

        return savedProduct;
    }

}

