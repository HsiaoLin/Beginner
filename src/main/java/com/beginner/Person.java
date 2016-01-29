package com.beginner;



class Person {

	private int id;

	private String name;

	private int age;

	public Person(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Person person = (Person) o;

		if (age != person.age)
			return false;
		if (id != person.id)
			return false;
		if (name != null ? !name.equals(person.name) : person.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + age;
		return result;
	}
}
