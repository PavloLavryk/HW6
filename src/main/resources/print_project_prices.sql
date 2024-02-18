SELECT
    project.ID AS NAME,
    SUM(worker.SALARY * (EXTRACT(YEAR FROM AGE(project.FINISH_DATE, project.START_DATE)) * 12 + EXTRACT(MONTH FROM AGE(project.FINISH_DATE, project.START_DATE)))) AS PRICE
FROM
    project
JOIN
    project_worker ON project.ID = project_worker.PROJECT_ID
JOIN
    worker ON project_worker.WORKER_ID = worker.ID
GROUP BY
    project.ID
ORDER BY
    PRICE DESC;