//
// Created by User on 21.03.2023.
//

#include <fstream>
#include <vector>
#include <cstdlib>

using namespace std;

struct Node {
private:
    Node *left;
    Node *right;
    long long key;
    long long height;
    long long msl;
public:

    Node(long long key) {
        this->key = key;
        this->left = this->right = nullptr;
        this->height = 0;
        this->msl = 0;
    }

    Node *getLeft() const {
        return left;
    }

    void setLeft(Node *left) {
        Node::left = left;
    }

    Node *getRight() const {
        return right;
    }

    void setRight(Node *right) {
        Node::right = right;
    }

    long long int getKey() const {
        return key;
    }

    void setKey(long long int key) {
        Node::key = key;
    }

    long long int getHeight() const {
        return height;
    }

    void setHeight(long long int height) {
        Node::height = height;
    }

    long long int getMsl() const {
        return msl;
    }

    void setMsl(long long int msl) {
        Node::msl = msl;
    }

};


class BST{
private:
    Node* root;
    Node* maxNode;
    long long bigMSL;

public:
    Node *getRoot() const {
        return root;
    }

    Node *getMaxNode() const {
        return maxNode;
    }

    BST(){
        this->root = nullptr;
    }

    void insert(long long key){
        this->root = insertRec(this->root, key);
    }

    void doTraversal(std::vector<long long> out, Node* node){
        if (node != nullptr){
            out.emplace_back(node->getKey());
            doTraversal(out, node->getLeft());
            doTraversal(out, node->getRight());
        }
    }

    void deleteVal(long long val){
        this->root =  deleteRec(this->root, val);
    }

    void  setMaxNode(Node* node){
        if (node != nullptr){
            setMaxNode(node->getLeft());
            setMaxNode(node->getRight());
            if (node->getLeft() == nullptr && node->getRight() == nullptr){
                node->setHeight(0);
                node->setMsl(0);
            }
            else if (node->getLeft() == nullptr){
                node->setHeight(node->getRight()->getHeight() + 1);
                node->setMsl(node->getRight()->getHeight() + 1);
            }
            else if (node->getRight() == nullptr){
                node->setHeight(node->getLeft()->getHeight() + 1);
                node->setMsl(node->getLeft()->getHeight() + 1);
            }
            else{
                if (node->getRight()->getHeight() > node->getLeft()->getHeight()){
                    node->setHeight(node->getRight()->getHeight() + 1);
                    node->setMsl(node->getLeft()->getHeight() + 2 + node->getRight()->getHeight());
                }
                else if(node->getLeft()->getHeight() > node->getRight()->getHeight()){
                    node->setHeight(node->getLeft()->getHeight() + 1);
                    node->setMsl(node->getLeft()->getHeight() + 2 + node->getRight()->getHeight());
                }
                else{
                    node->setHeight(node->getRight()->getHeight() + 1);
                    node->setMsl(node->getLeft()->getHeight() + 2 + node->getRight()->getHeight());
                }
            }

            if (node->getMsl() >= bigMSL){
                bigMSL = node->getMsl();
                maxNode = new Node(LONG_LONG_MIN);
                if (node->getHeight() >= maxNode->getHeight()){
                    maxNode = node;
                } else{
                    delete maxNode;
                }
            }
        }
    }

private:
    Node* deleteRec(Node* node, long long val){
        if (node == nullptr){
            return nullptr;
        }
        if (val < node->getKey()){
            node->setLeft(deleteRec(node->getLeft(), val));
        }
        if (val > node->getKey()){
            node->setRight(deleteRec(node->getRight(), val));
        }
        else{
            if (node->getLeft() == nullptr && node->getRight() == nullptr){
                return nullptr;
            }
            if (node->getLeft() == nullptr){
                return node->getRight();
            }
            if (node->getRight() == nullptr){
                return node->getLeft();
            }
            else{
                node->setKey(getSmallestValue(node->getRight()));
                node->setRight(deleteRec(node->getRight(), node->getKey()));
            }
        }
        return node;
    }

    long long getSmallestValue(Node* node){
        return node->getLeft() == nullptr ? node->getKey() : getSmallestValue(node->getLeft());
    }

    Node* insertRec(Node* node, long long val){
        if (node == nullptr){
            return nullptr;
        }
        if (val < node->getKey()){
            node->setLeft(insertRec(node->getLeft(), val));
        }
        if (val > node->getKey()){
            node->setRight(insertRec(node->getRight(), val));
        }
        return node;
    }

};

int main() {
    ifstream in("in.txt");
    ofstream out("out.txt");

    BST* tree = new BST();


    if (in.is_open()){
        string num;
        char *end;
        in >> num;
        while (!num.empty()){
            tree->insert(stoll(num));
            in >> num;
        }
    }
    in.close();


    tree->setMaxNode(tree->getRoot());
    tree->deleteVal(tree->getMaxNode()->getKey());

    vector<long long> vector;

    tree->doTraversal(vector, tree->getRoot());

    for (int i = 0; i < vector.size(); ++i) {
        out << vector[i] << "\n";
    }
    out.close();

    return 0;
}