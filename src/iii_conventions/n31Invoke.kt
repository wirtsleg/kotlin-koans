package iii_conventions

import util.TODO


class Invokable(private val numberOfInvocations:Int = 0) {

    operator fun invoke():Invokable = Invokable(numberOfInvocations + 1)

    fun getNumberOfInvocations():Int = numberOfInvocations
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
//    todoTask31()
    return invokable()()()().getNumberOfInvocations()
}
