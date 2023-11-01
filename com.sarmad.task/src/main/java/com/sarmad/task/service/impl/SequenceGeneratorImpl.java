package com.sarmad.task.service.impl;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sarmad.task.entities.DatabaseSequence;
import com.sarmad.task.service.ISequenceGenerator;
@Service
public class SequenceGeneratorImpl implements ISequenceGenerator {
	 private final MongoTemplate mongoTemplate;

	    @Autowired
	    public SequenceGeneratorImpl(MongoTemplate mongoTemplate) {
	        this.mongoTemplate = mongoTemplate;
	    }

	@Override
	public long generateSequence(String seqName) {
		
		 Query query = new Query(Criteria.where("_id").is(seqName));
	        Update update = new Update().inc("seq", 1);
	        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
	        DatabaseSequence counter = mongoTemplate.findAndModify(query, update, options, DatabaseSequence.class);
	        return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
