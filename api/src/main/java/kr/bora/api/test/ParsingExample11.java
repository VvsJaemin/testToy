package kr.bora.api.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ParsingExample11 {
    public static void main(String[] args) {
        int page = 1;

        try {
            String url = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=0ca2ecd93fe5f9eda2aaa86d2a206696&apiCode=ProductSearch&keyword=smart&option=Categories";
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Products");
            System.out.println("파싱할 리스트 수 : " + nList.getLength());

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElemnet = (Element) nNode;

                    System.out.println("-------------------");
                    System.out.println("제품 코드 " + getTagValue("ProductCode", eElemnet));
                    System.out.println("제품 명 " + getTagValue("ProductName", eElemnet));
                    System.out.println("제품 가격 " + getTagValue("ProductPrice", eElemnet));
                    System.out.println("제품 이미지 " + getTagValue("ProductImage", eElemnet));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if (nValue == null) {
            return null;
        }

        return nValue.getNodeValue();
    }
}
