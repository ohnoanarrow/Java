import java.util.NoSuchElementException;

class ArrayQueue<KEY> {

	KEY[] myData;
	int back, front, size;

	public ArrayQueue() {
		myData =(KEY[]) new Object[10];
		front = 0;
		back = 0;
		size = 0;
	} //ArrayQueue (constructor)

	public void add(KEY data) {
		if (size == myData.length) {
			throw new IllegalStateException("queue is full -- add");
		} //if

		myData[back] = data;
		back++;
		size++;

		if (back == myData.length) {
			back = 0;
		} //if
	} //add

	public KEY remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue is empty -- remove");
		} //if
		KEY temp = myData[front];
		myData[front] = null;
		front++;
		size--;

		if (front == myData.length) {
			front = 0;
		} //if

		return temp;
	} //remove

	public KEY element() {
		if (isEmpty()) {
			throw new NoSuchElementException("queue is empty -- element");
		} //if
		return myData[front];
	} //element

	public int size() {
		return size;
	} //size

	public boolean isEmpty() {
		return (size == 0);
	} //isEmpty

} //ArrayQueue (class)
