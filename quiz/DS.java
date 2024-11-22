package com.quiz;

class Queue1 {

    int front, rear, cap;
    Quetion[] a;

    public Queue1() {
        front = -1;
        rear = -1;
    }

    void setSize(int size) {
        cap = size;
        a = new Quetion[cap];
    }

    void enqueue(Quetion q) {
        if (rear >= cap - 1) {
            System.out.println("Queue is Overflow");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = rear + 1;
            a[rear] = q;
        }
    }

    Quetion dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is Underflow");
            return null;
        } else {
            Quetion item = a[front];
            front = front + 1;
            return item;
        }
    }

    boolean isEmpty() {
        return front == -1 || front > rear;
    }
}

class Linkedlist {

    class Node {
        Userd obj;
        Node next;

        public Node(Userd obj) {
            this.obj = obj;
            next = null;
        }

    }

    static Node first = null;

    void orderedInsert(Userd i) {
        Node n = new Node(i);
        if (first == null) {
            first = n;
        } else {
            if (first.obj.score < i.score) {
                addFirst(i);
                return;

            }
            Node temp = first;
            while (temp.next != null) {
                if (temp.next.obj.score < i.score) {
                    break;
                }
                temp = temp.next;
            }
            if (temp.next == null) {
                temp.next = n;
            } else {
                n.next = temp.next;
                temp.next = n;
            }
        }
    }

    void addFirst(Userd i) {
        Node n = new Node(i);
        if (first == null) {
            first = n;
        } else {
            n.next = first;
            first = n;
        }
    }

    void addLast(Userd i) {
        Node n = new Node(i);

        if (first == null) {
            first = n;
        } else {
            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

}

class Userd {
    String name;
    int score;
    int userid;
    int resultid;
    String quiztype;
    int wrongans;
}