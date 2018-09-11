package jp.ac.nig.ddbj.webapp.dstextsearch.controller;

import jp.ac.nig.ddbj.webapp.dstextsearch.dao.ResultInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.automaton.RegExp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.TreeMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Controller that demonstrates tiles mapping, reguest parameters and path variables.
 *
 * @author Mark Meany
 */
@Controller
public class MainController {
    private Log log = LogFactory.getLog(this.getClass());

    @Value("${index}")
    String index;


    @Value("${jbrowseBaseUrl}")
    String jbrowseBaseUrl;



    @RequestMapping(value = "/{species}/textsearch", method = GET)
    public ModelAndView home(
            @PathVariable(value="species") String species,
            @RequestParam(value = "query", defaultValue = "") String query,
            //@RequestParam(value="case", defaultValue="insensitive") String regexMode,
            @RequestParam(value = "page", defaultValue = "1") int page) {

        final int rowsPerPage = 50;

        IndexReader reader = null;
        IndexSearcher searcher = null;
        Analyzer analyzer = null;



        //int    rowsPerPage = 50;
        String totalHits = "0";
        int    totalPages = 0;

        ArrayList<ResultInfo> list = new ArrayList<>();
        ArrayList<Integer> pageList = new ArrayList<>();

        //IndexReader reader = null;
        try {

            reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));

            searcher = new IndexSearcher(reader);
            analyzer = new StandardAnalyzer();

            //QueryParser parser = new QueryParser("line", analyzer);
            //Query queryObj = parser.parse(query);
            Query queryObj = new RegexpQuery(new Term("line",
                    ".*" + query.toLowerCase() + ".*"), RegExp.INTERVAL|RegExp.COMPLEMENT);

            TopDocs tops = searcher.search(queryObj, page * rowsPerPage);
            totalHits = String.valueOf(tops.totalHits);
            ScoreDoc[] hits = tops.scoreDocs;

            totalPages = (int)Math.ceil((double) tops.totalHits / rowsPerPage);

            pageList = pagenation(page, totalPages);

            // Make the resultInfo list.
            //System.out.println("hits.length; " + String.valueOf(hits.length));
            for (int i=(page-1)*rowsPerPage; i<(int)Math.min(page*rowsPerPage, tops.totalHits); i++) {
                Document doc = searcher.doc(hits[i].doc);
                ResultInfo info = new ResultInfo(doc.get("line"), jbrowseBaseUrl);
                list.add(info);
            }


//            System.out.println("Query: " + query);
//            System.out.format("TotalHits: %s, TotalPages: %d\n", totalHits, totalPages);
//            System.out.format("Page: %d  (%d rows/page)\n", page, rowsPerPage);
//
//            StringJoiner j = new StringJoiner(" ");
//            for (Integer p : pageList) {
//                j.add(String.valueOf(p));
//            }
//            System.out.println("Pagenation: " + j.toString());
//
//
//            for (int i=0; i<list.size(); i++) {
//                System.out.println(list.get(i).getLine());
//            }



        } catch (IOException e) {
            e.printStackTrace();
        //} catch (ParseException e) {
        //    e.printStackTrace();
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


        TreeMap<String, Object> model = new TreeMap<>();
        model.put("species", species);
        model.put("query", query);
        model.put("page", page);
        model.put("totalHits", totalHits);
        model.put("totalPages", totalPages);
        model.put("pageList", pageList);
        model.put("resultInfoList", list);

        return new ModelAndView("site.homepage", model);

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





}
