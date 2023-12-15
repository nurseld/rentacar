package com.tobeto.pair2.services.concretes;

import com.tobeto.pair2.core.utilities.mapper.ModelMapperService;
import com.tobeto.pair2.entitites.Brand;
import com.tobeto.pair2.entitites.Car;
import com.tobeto.pair2.repositories.BrandRepository;
import com.tobeto.pair2.services.abstracts.BrandService;
import com.tobeto.pair2.services.dtos.brand.requests.AddBrandRequest;
import com.tobeto.pair2.services.dtos.brand.requests.DeleteBrandRequest;
import com.tobeto.pair2.services.dtos.brand.requests.UpdateBrandRequest;
import com.tobeto.pair2.services.dtos.brand.responses.GetByIdBrandResponse;
import com.tobeto.pair2.services.dtos.brand.responses.GetAllBrandResponse;
import com.tobeto.pair2.services.dtos.brand.responses.GetDeleteBrandResponse;
import com.tobeto.pair2.services.dtos.car.responses.GetAllCarResponse;
import com.tobeto.pair2.services.dtos.car.responses.GetByIdCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;


    @Override
    public void add(AddBrandRequest request) {
        if (brandRepository.existsBrandByName(request.getName())) {
            throw new RuntimeException("This brand already exists in the database.");
        }
        Brand brand = this.modelMapperService.forRequest().map(request, Brand.class);
        this.brandRepository.save(brand);

    }

    @Override
    public void update(UpdateBrandRequest request) {
        Brand brand = this.modelMapperService.forRequest().map(request, Brand.class);
        this.brandRepository.save(brand);

    }

    @Override
    public GetDeleteBrandResponse delete(DeleteBrandRequest request) {
        Brand brand = brandRepository.findById(request.getId()).orElseThrow();

        GetDeleteBrandResponse response = this.modelMapperService.forResponse().map(brand, GetDeleteBrandResponse.class);

        this.brandRepository.delete(brand);

        return response;

    }

    @Override
    public List<GetAllBrandResponse> getAll() {

        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandResponse> brandResponses = brands.stream()
                .map(brand -> this.modelMapperService.forResponse().map(brand,GetAllBrandResponse.class)).toList();

        return brandResponses;
    }

    @Override
    public GetByIdBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();

        GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand,GetByIdBrandResponse.class);

        return response;
    }

    @Override
    public boolean existsByBrandId(int brandId) {
        return brandRepository.existsById(brandId);
    }
}
