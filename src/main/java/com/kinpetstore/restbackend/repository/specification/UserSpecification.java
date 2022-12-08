package com.kinpetstore.restbackend.repository.specification;

import com.kinpetstore.restbackend.entity.User;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;


public class UserSpecification {

    private static final Logger logger = LogManager.getLogger(UserSpecification.class);

    public Specification<User> search(Map<String, Object> params) {

        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                List<Expression<Boolean>> expressions = predicate.getExpressions();

                logger.info("params2: {}", params);

                if (StringUtils.isNotEmpty((String) params.get("email"))) {
                    logger.info("email: {}", params.get("email"));
                    expressions.add(criteriaBuilder.like(root.get("email"), "%" + params.get("email") + "%"));
                }
                if (StringUtils.isNotEmpty((String) params.get("givenName"))) {
                    logger.info("givenName: {}", params.get("givenName"));
                    expressions.add(criteriaBuilder.like(root.get("givenName"), "%" + params.get("givenName") + "%"));
                }
                if (StringUtils.isNotEmpty((String) params.get("name"))) {
                    logger.info("name: {}", params.get("name"));
                    expressions.add(criteriaBuilder.like(root.get("name"), "%" + params.get("name") + "%"));
                }
                if (StringUtils.isNotEmpty((String) params.get("nickname"))) {
                    logger.info("nickname: {}", params.get("nickname"));
                    expressions.add(criteriaBuilder.like(root.get("nickname"), "%" + params.get("nickname") + "%"));
                }
                if (StringUtils.isNotEmpty((String) params.get("sub"))) {
                    logger.info("sub: {}", params.get("sub"));
                    expressions.add(criteriaBuilder.like(root.get("sub"), "%" + params.get("sub") + "%"));
                }
                if (StringUtils.isNotEmpty((String) params.get("picture"))) {
                    logger.info("picture: {}", params.get("picture"));
                    expressions.add(criteriaBuilder.like(root.get("picture"), "%" + params.get("picture") + "%"));
                }
                logger.info("expressions: {}", expressions);
                expressions.forEach(it -> logger.info(it));

                return predicate;
            }
        };
    }
}
