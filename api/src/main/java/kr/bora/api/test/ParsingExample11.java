package kr.bora.api.test;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ParsingExample11 {
    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if (nValue == null) {
            return null;
        }

        return nValue.getNodeValue();
    }
    public static void main(String[] args) {
        int page = 1;
        while (true){
            try {
                // key = 0ca2ecd93fe5f9eda2aaa86d2a206696
                String url = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=0ca2ecd93fe5f9eda2aaa86d2a206696&apiCode=ProductSearch&option=Categories&sortCd=L&keyword=";
                // 한글 keyword 파라미터 값 입력
                String subUrl = "자급제";
                //url 한글 인코딩 처리
                subUrl = URLEncoder.encode(subUrl, "UTF-8");

                url = url + subUrl;

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                doc.getDocumentElement().normalize();
                System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

                // 무엇을 파싱해야할지 태그명을 정확히 입력 해야 함.
                NodeList nList = doc.getElementsByTagName("Product");
                System.out.println("파싱할 리스트 수 : " + nList.getLength());

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        System.out.println("-------------------");
                        System.out.println("제품 코드 : " + getTagValue("ProductCode", eElement));
                        System.out.println("제품 명 :  " + getTagValue("ProductName", eElement));
                        System.out.println("제품 가격 :  " + getTagValue("ProductPrice", eElement));
                        System.out.println("제품 구매 페이지 : " + getTagValue("DetailPageUrl", eElement));
                        System.out.println("구매 평점 : " + getTagValue("DetailPageUrl", eElement));
                    }
                }
                page += 1;
                System.out.println("page number : " + page);
                if (page > 5) {
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
