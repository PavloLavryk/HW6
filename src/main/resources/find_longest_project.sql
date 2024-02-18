SELECT project.ID, project.START_DATE, project.FINISH_DATE, (project.FINISH_DATE - project.START_DATE) AS project_duration
FROM project
ORDER BY project_duration DESC
LIMIT 1;