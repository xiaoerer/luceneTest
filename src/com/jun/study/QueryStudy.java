package com.jun.study;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

public class QueryStudy {
	
	public static void main(String[] args) {
		String key="极客学院";
		String field="name";
		String[] fields={"name","content"};
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_43);
		Query query=null;
		QueryParser parser=new QueryParser(Version.LUCENE_43,field,analyzer);
		try {
			query=parser.parse(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(QueryParser.class+query.toString());
		
		MultiFieldQueryParser parser1=new MultiFieldQueryParser(Version.LUCENE_43,fields,analyzer);
		try {
			query=parser1.parse(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(MultiFieldQueryParser.class+query.toString());
		
		
		
	}

}
