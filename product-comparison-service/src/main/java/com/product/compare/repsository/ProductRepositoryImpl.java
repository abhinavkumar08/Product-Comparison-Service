package com.product.compare.repsository;

import com.product.compare.document.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private MongoTemplate mongoTemplate;

    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Product addProduct(Product product, String category) throws Exception {
        mongoTemplate.indexOps(category).ensureIndex(new Index("name", Sort.Direction.ASC).unique());
        return mongoTemplate.save(product, category);
    }

    @Override
    public Product getProduct(String name, String category) throws Exception {

        Criteria criteria = Criteria.where("name").is(name);
        Query query = new Query(criteria);
        Product product = mongoTemplate.findOne(query, Product.class, category);
        return product;
    }

}
