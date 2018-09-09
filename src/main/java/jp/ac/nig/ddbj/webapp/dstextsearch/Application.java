package jp.ac.nig.ddbj.webapp.dstextsearch;

import jp.ac.nig.ddbj.webapp.dstextsearch.dao.ResultInfo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by Spring Source Tool Suite.
 * 
 * @author Mark Meany
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


/*


    public void testIndex(String query, int page) {

        int    rowsPerPage = 50;
        String totalHits = "0";

        ArrayList<ResultInfo> list = new ArrayList<>();
        ArrayList<Integer> pageList = new ArrayList<>();

        IndexReader reader = null;
        try {

            reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));

            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer();

            QueryParser parser = new QueryParser("line", analyzer);
            Query queryObj = parser.parse(query);

            TopDocs tops = searcher.search(queryObj, page * rowsPerPage);
            totalHits = String.valueOf(tops.totalHits);
            ScoreDoc[] hits = tops.scoreDocs;

            int totalPages = (int)Math.ceil((double) tops.totalHits / rowsPerPage);

            pageList = pagenation(page, totalPages);

            // Make the resultInfo list.
            //System.out.println("hits.length; " + String.valueOf(hits.length));
            for (int i=(page-1)*rowsPerPage; i<(int)Math.min(page*rowsPerPage, tops.totalHits); i++) {
                Document doc = searcher.doc(hits[i].doc);
                ResultInfo info = new ResultInfo(doc.get("line"));
                list.add(info);
            }


            System.out.println("Query: " + query);
            System.out.format("TotalHits: %s, TotalPages: %d\n", totalHits, totalPages);
            System.out.format("Page: %d  (%d rows/page)\n", page, rowsPerPage);

            StringJoiner j = new StringJoiner(" ");
            for (Integer p : pageList) {
                j.add(String.valueOf(p));
            }
            System.out.println("Pagenation: " + j.toString());


            for (int i=0; i<list.size(); i++) {
                System.out.println(list.get(i).getLine());
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    public ArrayList<Integer> pagenation(int page, int totalPages) {

        final int pagenationLength = 10;

        ArrayList<Integer> pageList = new ArrayList<Integer>();

        int start = Math.max(page - pagenationLength/2+1, 1);
        int end   = Math.min(page + pagenationLength/2, totalPages);

        for (int i=start; i<=end; i++) {
            pageList.add(i);
        }

        return pageList;

    }
    */

}

