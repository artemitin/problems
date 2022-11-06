package test.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a valid XML document, construct a tree structure that correctly represents the hierarchical relations between the nodes in the XML document.

As support, an XML Node class is defined in the lesson.
Each node stores the name of the node and, the list of its child nodes.
We will not store XML attributes, only the name of the node.

Additionally, an XML Tokenizer class is also provided.
This class implements the Get Next Element function that can be called in a loop to return all the XML tokens in the string, classified as one of:

Element opening tag
Element closing tag
Element text
Unknown element (a catch-all for unrecognized tokens)
 */
public class XmlToTree {

    public static void main(String[] args) {
        NodeXml result = createXmlTree(
                "<html>" +
                "  <body>" +
                "    <div>" +
                "      <h1>CodeRust</h1>" +
                "      <a>http://coderust.com</a> " +
                "    </div>" +
                "    <div>" +
                "        <h2>Chapter 1</h2>" +
                "    </div>" +
                "    <div>" +
                "        <h3>Chapter 2</h3>" +
                "        <h4>Chapter 2.1</h4>" +
                "    </div>" +
                "  </body>" +
                "</html>");
        System.out.println(result);
    }

    public static NodeXml createXmlTree(String xml) {
        // parse the received string for XML tokens
        XmlTokenizer tok = new XmlTokenizer(xml);

        XmlElement element = new XmlElement();
        if (!tok.getNextElement(element)) {
            // return null if no tokens are found in parsing
            return null;
        }
        System.out.println(element);
        List<NodeXml> st = new ArrayList<>();

        // set up the tree root
        NodeXml root = new NodeXml(element.nodeName);

        // push it on to the stack
        st.add(root);

        while(tok.getNextElement(element)) {
            System.out.println(element);
            NodeXml node = new NodeXml(element.nodeName);
            switch (element.elementType) {
                case 2://opening tag, push new element to stack
                    st.get(st.size() - 1).children.add(node);
                    st.add(node);
                    break;
                case 4: //text node
                    st.get(st.size() - 1).children.add(node);
                    break;
                case 3: //closing tag
                    // else if the current element is a closing tag, pop from the tag as it has been
                    // processed
                    st.remove(st.size() - 1);
                    break;
            }
        }

        return root;
    }
}

class NodeXml {
    public String nodeName;
    public List<NodeXml> children;

    public NodeXml(String data) {
        this.nodeName = data;
        children = new ArrayList<>();

    }
}

class XmlElement {
    int elementType;
    String nodeName;
    public static Map<String, Integer> xmlElementType = new HashMap<>() {
        {
            put("ELEMENT_UNKNOWN", 1);
            put("ELEMENT_OPENING_TAG", 2);
            put("ELEMENT_CLOSING_TAG", 3);
            put("ELEMENT_TEXT", 4);
        }
    };

    XmlElement() {
        elementType = xmlElementType.get("ELEMENT_UNKNOWN");
        nodeName = "";
    }

    @Override
    public String toString() {
        return "XmlElement{elementType=" + elementType + ", nodeName='" + nodeName + "'}";
    }
}

class XmlTokenizer {
    String xml;
    int currentIndex;

    XmlTokenizer(String xmlStr) {
        xml = xmlStr;
        currentIndex = 0;
    }

    Boolean getNextElement(XmlElement element) {
        int i = this.xml.substring(currentIndex).indexOf('<');
        if (i == -1) {
            return false;
        }
        i += currentIndex;

        String temp = this.xml.substring(currentIndex, i);
        temp = temp.trim();

        if (!temp.isEmpty()) {
            element.nodeName = temp;
            element.elementType = XmlElement.xmlElementType.get("ELEMENT_TEXT");
            currentIndex = i;
            return true;
        }

        int j = this.xml.substring(i).indexOf('>');
        if (j == -1) {
            return false;
        }

        j += i;
        if (this.xml.charAt(i + 1) == '/') {
            element.nodeName = this.xml.substring(i + 2, j);
            element.elementType = XmlElement.xmlElementType.get("ELEMENT_CLOSING_TAG");
        } else {
            element.nodeName = this.xml.substring(i + 1, j);
            element.elementType = XmlElement.xmlElementType.get("ELEMENT_OPENING_TAG");
        }

        this.currentIndex = j + 1;
        return true;
    }
}
