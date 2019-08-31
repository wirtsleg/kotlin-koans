package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
    operator fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }

    operator fun plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval, 1)
    operator fun plus(interval: RepeatedTimeInterval): MyDate = this.addTimeIntervals(interval.timeInterval, interval.number)

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(multiplier: Int): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, multiplier)
    }

}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {

    operator fun contains(value: MyDate): Boolean {
        return start <= value && value <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var currentDate: MyDate = start

            override fun hasNext(): Boolean = this.currentDate <= endInclusive

            override fun next(): MyDate {
                val result = currentDate
                currentDate = currentDate.nextDay()
                return result
            }
        }
    }

}
