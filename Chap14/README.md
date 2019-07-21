# Chapter 14 - Databases

- Difference between `JOIN`, `INNER JOIN`, `NATURAL JOIN`, `LEFT OUTER JOIN`, `RIGHT OUTER JOIN`, `FULL OUTER JOIN`
- Normalized databases are designed to minimize redundancy, while denormalized databased are designed to optmize query (especially read) time (which would create redundancy).
- We are in favor of PostgreSQL dialect here.

## Exercises

#### 14.0 Schooling

```sql
SELECT DISTINCT s.StudentID, s.StudentName, (
	SELECT COUNT(DISTINCT e.CourseID)
	FROM StudentCourses e
	WHERE e.StudentID = s.StudentID
) AS count
FROM Students s;
```

```sql
WITH CourseSize AS (
	SELECT DISTINCT c.CourseID, c.TeacherID, (
		SELECT COUNT(DISTINCT e.StudentID)
		FROM StudentCourses e
		WHERE e.CourseID = c.CourseID
	) AS size
	FROM Courses c
)
SELECT DISTINCT t.TeacherID, t.TeacherName, (
	SELECT SUM(s.size)
	FROM CourseSize s
	WHERE s.TeacherID = t.TeacherID
) AS num
FROM Teachers t
ORDER BY num DESC;
```

#### 14.1 Multiple Apartments

```sql
SELECT m1.TenantID
FROM AptTenants m1, AptTenants m2
WHERE m1.TenantID = m2.TenantID AND m1.AptID <> m2.AptID;
```

#### 14.2 Open Requests

```sql
SELECT *
FROM Apartments a, Buildings b, Requests r
WHERE a.BuildingID = b.BuildingID AND a.AptID = r.AptID and r.Status = 'Open';
```

#### 14.3 Close All Requests

```sql
UPDATE Requests r
SET r.Status = 'Closed'
WHERE r.AptID IN (
	SELECT a.AptID
	FROM Apartments a
	WHERE a.BuildingID = 11
);
```

#### 14.4 Joins

Inner join, natural join, left/right/full outer join, anti-join, semi-join.

#### 14.5 Denormalization

Flatten data structure.

#### 14.6 Entity-Relationship Diagram

Companies and people are associated by professionals.

#### 14.7 Design Grade Database

Students, Courses, Enrollments.
