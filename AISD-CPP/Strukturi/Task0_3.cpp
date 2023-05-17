#include <iostream>
#include <fstream>
#include <utility>
#include <vector>

struct Node{
private:
     long long value;
     std::string direction;
     long long minB, maxB;
public:
    Node(long value, std::string direction) : value(value),direction(std::move(direction)){}

    long long getValue() const {
        return value;
    }

    const std::string &getDirection() const {
        return direction;
    }

    long long getMinB() const {
        return minB;
    }

    void setMinB(long long minB) {
        Node::minB = minB;
    }

    long long getMaxB() const {
        return maxB;
    }

    void setMaxB(long long maxB) {
        Node::maxB = maxB;
    }

};

void setBorders(std::vector<Node> nodes, int parentLine, int i){
    Node node = nodes[i];
    Node parent = nodes[parentLine - 1];
    if (node.getDirection() == "L"){
        node.setMaxB(parent.getValue());
        node.setMinB(parent.getMinB());
    }
    else {
        node.setMinB(parent.getValue());
        node.setMaxB(parent.getMaxB());
    }
}


bool isBST(std::vector<Node> nodes){
    bool isBST = true;

    for (int i = 1; i < nodes.size(); i++) {
        if (nodes[i].getDirection() == "L"){
            if (!(nodes[i].getMaxB() > nodes[i].getValue() && nodes[i].getMinB() < nodes[i].getValue())){
                return false;
            }
        }
        else{
            if (!(nodes[i].getMinB() <= nodes[i].getValue() && nodes[i].getMaxB() > nodes[i].getValue())){
                return false;
            }
        }
    }

    return isBST;
}

int main(){
    std::ifstream in("bst.in");
    std::ofstream out("bst.out");

    int size;
    std::vector<Node> vector;
    if (in.is_open()){
        in >> size;
        int firstVal;
        in >> firstVal;
        vector.emplace_back(firstVal, "ROOT");
        vector[0].setMinB(LONG_LONG_MIN);
        vector[0].setMaxB(LONG_LONG_MAX);
        for (int i = 1; i < size; ++i) {
            int value;
            in >> value;
            int parentLine;
            in >> parentLine;
            std::string direction;
            in >> direction;
            vector.emplace_back(Node(value,direction));
            setBorders(vector, parentLine, i);
        }
    }
    in.close();


    if (isBST(vector)){
        out << "YES";
    }
    else{
        out << "NO";
    }
    out.close();
}