#include <stdio.h>
#include <stdlib.h>

typedef struct node {
    int value;
    struct node* next;
} node;

typedef struct my_queue {
    struct node* front;
    struct node* back;
}  my_queue;

my_queue* create_new();
my_queue* add_element(my_queue*, const int);
my_queue* remove_element(my_queue*);
my_queue* free_queue(my_queue*);
void print_queue(my_queue*);

my_queue* create_new() {
    my_queue* q = malloc(sizeof(*q));
    if(q == NULL)
        fprintf(stderr, "Malloc failed\n");
    q->front = q->back = NULL;
    return q;
}

my_queue* add_element(my_queue* q, const int value) {
    node* n = malloc(sizeof(*n));
    if(n == NULL) {
        fprintf(stderr, "Malloc failed\n");
        return q;
    }
    n->value = value;
    n->next = NULL;

    if(q->front == NULL && q->back == NULL) {
        q->front = q->back = n;
        return q;
    }
    
    q->back->next = n;
    q->back = n;
    return q;
}

my_queue* remove_element(my_queue* q) {
    node* node1 = NULL;
    node* node2 = NULL;
    
    if(q->front == NULL) {
    	fprintf(stdout, "Cannot remove: queue is empty\n");
    	return q;
    }
    
    node1 = q->front;
    node2 = node1->next;
    free(node1);
    q->front = node2;

    if(q->front == NULL) {
        q->back = q->front;
    }
    return q;
}

void print_queue(my_queue* q) {
	printf("Queue: ");
	if(q->front == NULL) {
    	fprintf(stdout, "empty\n");
    	return;
    }
    
    node* p = q->front;
    while(p != NULL) {
    	printf("%d  ", p->value);
    	p = p->next;
    }
    printf("\n");
}

my_queue* free_queue(my_queue* q) {
    while(q->front) {
        remove_element(q);
    }
    return q;
}

int main() {
    my_queue* q = NULL;
    q = create_new();
    print_queue(q);
    q = add_element(q, 12);
    q = add_element(q, 13);
    q = add_element(q, 14);
    printf("Starting queue:\n");
    print_queue(q);
    
    q = remove_element(q);
    printf("After removing...\n");
    print_queue(q);
    
    q = remove_element(q);
    printf("After removing...\n");
    print_queue(q);
    
    q = add_element(q, 25);
    printf("After adding...\n");
    print_queue(q);
    
    printf("Removing all elements\n");
    q = remove_element(q);
    q = remove_element(q);
    printf("After removing...\n");
    
    print_queue(q);
    print_queue(q);
    q = remove_element(q);
}