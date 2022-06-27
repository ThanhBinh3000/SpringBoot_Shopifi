package com.tecapro.demobinhan.service.image;

import com.tecapro.demobinhan.model.Image;
import com.tecapro.demobinhan.model.Product;
import com.tecapro.demobinhan.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Iterable<Image> findAllByProduct(Product product) {
        return imageRepository.findAllByProduct(product);
    }
}
