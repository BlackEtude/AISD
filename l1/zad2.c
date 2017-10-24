#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 1000
#define CHECK 1000
#define MAX 200

typedef struct node {
    int value;
    struct node* next;
} node;

typedef struct list {
    int count;
    struct node* first;
    struct node* last;
} list;

void create_list(list*);
void print_list(list*);
void push_element(list*, int);
int remove_element(list*, int);
int get_element(list*, int);
void merge_lists(list*, list*);
void free_list(list* list);

void create_list(list* list) {
    list->first = NULL;
    list->last = NULL;
    list->count = 0;
}

void free_list(list* list) {
    node* tmp = list->first;
    node* curr = NULL;

    while(tmp) {
        curr = tmp;
        tmp = tmp->next;
        free(curr);
        curr = NULL;
    }
    list->first = NULL;
    list->last = NULL;
    list->count = 0;
    list = NULL;
}

int remove_element(list* list, int n) {
    int ret = -1;
    node* curr = list->first;
    node* temp = NULL;

    if(n >= list->count)
        return -1;

    if (n == 0) {
        ret = curr->value;
        temp = curr->next;
        list->count--;
        list->first = temp;
        free(curr);
    }
    else {
        for (int i = 0; i < n-1; i++) {
            if (curr->next == NULL)
                return -1;
            curr = curr->next;
        }
        temp = curr->next;
        ret = temp->value;
        curr->next = temp->next;
        free(temp);
        list->count--;
    }
    return ret;
}

void push_element(list* list, int val) {
    node* tmp = malloc(sizeof(node));
    if(tmp == NULL) {
        fprintf(stdout, "Malloc failed\n");
        return;
    }

    tmp->value = val;
    tmp->next = NULL;

    if(list->last == NULL && list->first == NULL) {
        list->count = list->count+1;
        list->first = list->last = tmp;
    }
    else {
        list->last->next = tmp;
        list->last = tmp;
        list->count = (list->count + 1);
    }
}

void print_list(list* list) {
    printf("List: ");
    node* current = list->first;
    while(current != NULL) {
        printf("%d ", current->value);
        current = current->next;
    }
    printf("\nList size: %d\n", list->count);
}

void merge_lists(list* list1, list* list2) {
    if(list1->first == NULL) {
        *list1 = *list2;
        list2->first = NULL;
        list2->last = NULL;
    }
    else {
        list1->count += list2->count;
        list1->last->next = list2->first;
        list1->last = list2->last;
        list2->first = NULL;
        list2->last = NULL;
        list2->count = 0;
        list2 = NULL;
    }
}

int get_element(list* list, int elem) {
    node* tmp = list->first;
    int c = 0;
    while(tmp && c < elem) {
        tmp = tmp->next;
        c++;
    }

    if(tmp == NULL)
        return -1;
    else
        return tmp->value;
}

void measure_average() {
    srand(time(NULL));
    struct timeval t1, t2;
    list list;
    create_list(&list);
    double avg = 0;
    int number;

    for(int i = 0; i < SIZE; i++)
        push_element(&list, (rand() % MAX));

    FILE* file;
    file = fopen("zad2s_avg.txt", "w");
    for(int i = 0; i < SIZE; i++) {
        avg = 0;
        for(int j = 0; j < CHECK; j++) {
            gettimeofday(&t1, NULL);
            number = get_element(&list, i);
            gettimeofday(&t2, NULL);
            double time = ((t2.tv_sec - t1.tv_sec) * 1000000L + t2.tv_usec) - t1.tv_usec;
            avg += time;
        }
        avg /= CHECK;
        fprintf(file, "%d;%f\n", i, avg);
    }
    fclose(file);

    file = fopen("zad2r_avg.txt", "w");
    for(int i = 0; i < SIZE; i++) {
        avg = 0;
        for(int j = 0; j < CHECK; j++) {
            gettimeofday(&t1, NULL);
            number = get_element(&list, rand() % SIZE);
            gettimeofday(&t2, NULL);
            double time = ((t2.tv_sec - t1.tv_sec) * 1000000L + t2.tv_usec) - t1.tv_usec;
            avg += time;
        }
        avg /= CHECK;
        fprintf(file, "%d;%f\n", i, avg);
    }
    fclose(file);

    free_list(&list);
}

int main() {
    srand(time(NULL));
    list list1, list2;
    create_list(&list1);
    create_list(&list2);

    for(int i = 0; i < 5; i++) {
        int r = rand() % 101;
        printf("Adding %d\n", r);
        push_element(&list1, r);
    }
    print_list(&list1);

    for(int i = 0; i < 5; i++) {
        int r = rand() % 101;
        printf("Adding %d\n", r);
        push_element(&list2, r);
    }
    print_list(&list2);

    merge_lists(&list1, &list2);
    print_list(&list1);
    print_list(&list2);

    free_list(&list1);
    free_list(&list2);

    measure_average();

//    int rem = remove_element(&list1, 0);
//    printf("Removing: %d\n", rem);
//    print_list(&list1);

//    rem = remove_element(&list1, 0);
//    printf("Removing: %d\n", rem);
//    print_list(&list1);
//
//    rem = remove_element(&list1, 1);
//    printf("Removing: %d\n", rem);
//    print_list(&list1);
//
//    rem = remove_element(&list1, 0);
//    printf("Removing: %d\n", rem);
//    print_list(&list1);
//
//    rem = remove_element(&list1, 0);
//    printf("Removing: %d\n", rem);
//    print_list(&list1);
}