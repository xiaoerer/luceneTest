package com.jun.study;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexCreate {
	
	public static void main(String[] args) {
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_43);
		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_43, analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);//没有就新建 有就打开
		
		Directory directory=null;
		IndexWriter indexWriter=null;
		try {
			directory=FSDirectory.open(new File("C://index/test"));
			if(IndexWriter.isLocked(directory)){
				IndexWriter.unlock(directory);
			}
			indexWriter=new IndexWriter(directory, indexWriterConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc1=new Document();
		doc1.add(new StringField("id", "abcde", Store.YES));
		doc1.add(new TextField("content", "极客学院", Store.YES));
		doc1.add(new IntField("num", 1, Store.YES));
		
		try {
			indexWriter.addDocument(doc1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc2=new Document();
		doc2.add(new StringField("id", "asdff", Store.YES));
		doc2.add(new TextField("content", "Lucene案例开发", Store.YES));
		doc2.add(new IntField("num", 2, Store.YES));
		
		try {
			indexWriter.addDocument(doc2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			indexWriter.commit();
			indexWriter.close();
			directory.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
