package com.bridgelabz.fundoo.elasticSearch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.util.TokenGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Rohan kadam
 * purpose:
 *
 */
@Service
public class ElasticSearch implements IElasticSearch {
	String INDEX = "notedb";
	String TYPE = "notetype";

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TokenGenerators tokengenerators;
	
	
	@Override
	public Notes create(Notes notes) {
		@SuppressWarnings("unchecked")
		Map<String, Object> dataMap = objectMapper.convertValue(notes, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, String.valueOf(notes.getId())).source(dataMap);

		try {
			client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return notes;

	}

	@Override
	public Notes updateNote(Notes notes) {
		@SuppressWarnings("unchecked")
		Map<String, Object> dataMap = objectMapper.convertValue(notes, Map.class);
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, String.valueOf(notes.getId()));

		updateRequest.doc(dataMap);
		try {
			client.update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notes;
	}

	@Override
	public void deleteNote(Long NoteId) {
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, String.valueOf(NoteId));
		try {
			client.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
	}

	@Override
	public List<Notes> searchData()  {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse=null;
        try {
 searchResponse =
client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return  getSearchResult(searchResponse); 
	}


	private List<Notes> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Notes> notes = new ArrayList<>();

        if (searchHit.length > 0) {

            Arrays.stream(searchHit)
                    .forEach(hit -> notes
                            .add(objectMapper
                                    .convertValue(hit.getSourceAsMap(),
                                                    Notes.class))
                    );
        }

return notes;
	
	}

	@Override
	public List<Notes> searchall(String query, String token) throws IllegalArgumentException, UnsupportedEncodingException {
		long userId=tokengenerators.decodeToken(token);
		SearchRequest searchRequest = new SearchRequest(INDEX).types(TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery("*" + query + "*")
                .analyzeWildcard(true).field("title").field("description"))
                .filter(QueryBuilders.termsQuery("userId", String.valueOf(userId)));
        System.out.println();
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(searchResponse);
        } catch (IOException e) {

            e.printStackTrace();
        }

        List<Notes> allnote = getSearchResult(searchResponse);

        return allnote;
	}
}
