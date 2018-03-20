package g.takeru.renshu.leetcode

import timber.log.Timber

/**
 * Created by takeru on 2018/3/20.
 */
class ReverseLinkedList (){

    /**
     * Reverse a singly linked list.
     *
     * Hint:
     * A linked list can be reversed either iteratively or recursively. Could you implement both?
     *
     * Example
     * For linked list 1->2->3, the reversed linked list is 3->2->1
     */

    fun testing () {
        val a = ListNode(1)
        val b = ListNode(2)
        val c = ListNode(3)
        a.next = b
        b.next = c
        c.next = null

        // origin
        printLinkedList(a)
        // reverse
        printLinkedList(solution1(a))
        // reverse again
        printLinkedList(solution2(c))
    }

    class ListNode(var value: Int = 0) {
        var next: ReverseLinkedList.ListNode? = null
    }

    private fun solution1(head: ListNode?): ListNode? {
        var prev : ListNode? = null
        var curr = head

        while (curr != null) {
//            Timber.d("-- curr: ${curr?.value} -> ${curr?.next?.value}" )
            var nextTemp = curr.next
            curr.next = prev
            prev = curr
            curr = nextTemp
//            Timber.d("curr: ${curr?.value} -> ${curr?.next?.value}" )
        }

        return prev
    }

    private fun solution2(head: ListNode?): ListNode? {
        if (head?.next == null)
            return head
        val p = solution2(head.next!!)
        head.next?.next = head
        head.next = null
        return p
    }

    private fun printLinkedList(head: ListNode?) {
        var curr = head
        var linkedList = ""
        while (curr != null) {
            linkedList += curr.value.toString() + " -> "
//            Timber.d("curr: ${curr?.value} -> ${curr?.next?.value}")
            curr = curr.next
        }
        linkedList += "null"
        Timber.d(linkedList)
    }
}