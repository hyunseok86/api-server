package com.api.service;

import com.api.entity.Person;
import com.api.repository.PersonRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Slf4j
public class OpenApiService {

    private final RestTemplate restTemplate;


    public OpenApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    private PersonRepository personRepo;


    public void getPersonPhotoWebScraper(){
        try {
            //https://ampos.nanet.go.kr:7443/activityCongressListInner.do?assemblyTh=21&commitName=&partyNm=&choCongressName=&queryText_bak=&curPage=2&queryGubun=&queryText=
            String baseUrl = "https://ampos.nanet.go.kr:7443/activityCongressListInner.do";
            String queryParams = "#assemblyTh=21&commitName=&partyNm=&choCongressName=&queryText_bak=&queryGubun=&queryText=";
            String curPageParam = "&curPage=";


            for (int i = 1; i <= 1; i++) {
                URL url = new URL(baseUrl + "?" + queryParams + curPageParam + i);
                Document doc = Jsoup.parse(url, 3000); // URL에서 HTML 소스코드 파싱
//                System.out.println("doc " + doc); // 선택한 요소의 텍스트 출력
                Elements elements = doc.select("div.rightContainer img.btn_detail");
                Elements elementsName = doc.select("div.rightContainer a.btn_detail");
                Elements elementsParty = doc.select("div.rightContainer span.blueRound");

                System.out.println("elements " + elements); // 선택한 요소의 텍스트 출력
                for (Element element : elements) {
                    String imgUrl = element.attr("src");
                    if(null!=imgUrl){
                        Element nameElement = element.selectFirst("a.btn_detail");
                    }
                }
                for (Element element : elementsName) {
                    Element nameElement = element.selectFirst("a.btn_detail");
                    if(null!=nameElement) {
                        String name = nameElement.text();
                        System.out.println("::::::: " + name);
                    }
                }

                for (Element element : elementsParty) {
                    Element nameElement = element.selectFirst("span.blueRound");
                    if(null!=nameElement){
                        String name = nameElement.text();
                        System.out.println("::::::: "+name);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPsersons(int code) throws ParseException {

        String mapCode = "nwvrqwxyaytdsfvhu";
//        String url = "https://open.assembly.go.kr/portal/openapi/npffdutiapkzbfyvr?KEY=bb44ad9d9cc2438ea37a7f84ff5308d6&Type=json&pIndex=1&pSize=1000&UNIT_CD="+String.valueOf(code);
        String url = "https://open.assembly.go.kr/portal/openapi/nwvrqwxyaytdsfvhu?key=bb44ad9d9cc2438ea37a7f84ff5308d6&type=json&pSize=500&pIndex=1";
        String response = restTemplate.getForObject(url, String.class);
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();

        JsonArray data = (JsonArray) json.get(mapCode);
        if(null == data ) return "";
        for (JsonElement jsonElement : data) {
            if (jsonElement instanceof JsonObject) {

                JsonObject jsonObject = (JsonObject) jsonElement;
                if(jsonObject.has("row")){
                    JsonArray row = (JsonArray) jsonObject.get("row");
                    for (JsonElement jsonElement2 : row) {
                        if(jsonElement2.isJsonObject()){
                            JsonObject jobj = jsonElement2.getAsJsonObject();

                            try {
                                String HG_NM = jobj.get("HG_NM").toString().replaceAll("\"", "");
                                String HJ_NM = jobj.get("HJ_NM").toString().replaceAll("\"", "");
                                String ENG_NM = jobj.get("ENG_NM").toString().replaceAll("\"", "");
                                String SEX_GB = jobj.get("SEX_GBN_NM").toString().replaceAll("\"", "");
                                String reElection = jobj.get("REELE_GBN_NM").toString().replaceAll("\"", "");
                                String ELECT_GBN_NM = jobj.get("ELECT_GBN_NM").toString().replaceAll("\"", "");
                                String BTH_GBN_NM = jobj.get("BTH_GBN_NM").toString().replaceAll("\"", "");
                                String BTH_DATE = jobj.get("BTH_DATE").toString().replaceAll("\"", "");
                                String ORIG_NM = jobj.get("ORIG_NM").toString().replaceAll("\"", "");
//                                String unitCode = jobj.get("UNIT_CD").toString().replaceAll("\"", "");
                                String monaCode = jobj.get("MONA_CD").toString().replaceAll("\"", "");
                                String POLY_NM = jobj.get("POLY_NM").toString().replaceAll("\"", "");
                                String CMIT_NM = jobj.get("CMIT_NM").toString().replaceAll("\"", "");
                                String CMITS = jobj.get("CMITS").toString().replaceAll("\"", "");
                                String TEL_NO = jobj.get("TEL_NO").toString().replaceAll("\"", "");
                                String E_MAIL = jobj.get("E_MAIL").toString().replaceAll("\"", "");
                                String HOMEPAGE = jobj.get("HOMEPAGE").toString().replaceAll("\"", "");
                                String STAFF = jobj.get("STAFF").toString().replaceAll("\"", "");
                                String SECRETARY = jobj.get("SECRETARY").toString().replaceAll("\"", "");
                                String SECRETARY2 = jobj.get("SECRETARY2").toString().replaceAll("\"", "");
                                String MEM_TITLE = jobj.get("MEM_TITLE").toString().replaceAll("\"", "");
                                String ASSEM_ADDR = jobj.get("ASSEM_ADDR").toString().replaceAll("\"", "");

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date parsedDate = dateFormat.parse(BTH_DATE);
                                Timestamp timestamp = new Timestamp(parsedDate.getTime());
                                Person insertData = Person.builder()
                                        .engName(ENG_NM)
                                        .hjName(HJ_NM)
                                        .hgName(HG_NM)
                                        .sex(SEX_GB)
                                        .birthType(BTH_GBN_NM)
                                        .birth(timestamp)
                                        .reElection(reElection)
                                        .origName(ORIG_NM)
                                        .monaCode(monaCode)
                                        .cmitName(CMIT_NM)
                                        .cmits(CMITS)
                                        .polyName(POLY_NM)
                                        .origName(ORIG_NM)
                                        .telNo(TEL_NO)
                                        .email(E_MAIL)
                                        .staff(STAFF)
                                        .secretary(SECRETARY)
                                        .secretary2(SECRETARY2)
                                        .menTitle(MEM_TITLE)
                                        .assemAddr(ASSEM_ADDR)
                                        .homePage(HOMEPAGE)
                                        .electType(ELECT_GBN_NM).build();

                                personRepo.save(insertData);
                            }catch (Exception e){
                                System.out.println("## ERROR "+ e);

                            }
                        }

                    }




                }

            }
        }

        return response;
    }
}