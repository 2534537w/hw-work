#include "llrec.h"


#include <iostream>

using namespace std;

//*********************************************
// Provide your implementation of llpivot below
//*********************************************


static Node *partition(Node *head, int x) {
    if (!head)
        return head;
    Node *tmp = partition(head->next, x);

    if (head->val <= x) {
        head->next = tmp;
        return head;
    }

    Node *dy = new Node(-1, tmp);
    Node *prev = dy;
    while (tmp) {
        if (tmp->val > x) {
            prev->next = head;
            head->next = tmp;
            return dy->next;
        }
        prev = tmp;
        tmp = tmp->next;
    }
    prev->next = head;
    head->next = nullptr;
    Node *ret = dy->next;
    delete dy;
    return ret;
}

static void print(Node *head) {
    while (head) {
        cout << head->val << " ";
        head = head->next;
    }
    cout << endl;
}

void llpivot(Node *&head, Node *&smaller, Node *&larger, int pivot) {
    Node *tmp = partition(head, pivot);
    printf("tmp\n");
    print(tmp);

    head = nullptr;

    Node *prev = nullptr;
    Node *curr = tmp;

    while (curr && curr->val <= pivot) {
        prev = curr;
        curr = curr->next;
    }

    if (prev) {
        prev->next = nullptr;
    }
    if (tmp->val < pivot)
        smaller = tmp;
    else
        smaller = nullptr;
    larger = curr;
}

