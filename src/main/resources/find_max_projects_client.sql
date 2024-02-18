SELECT client.ID, client.NAME, COUNT(project.ID) AS project_count
FROM client
JOIN project ON client.ID = project.CLIENT_ID
GROUP BY client.ID, client.NAME
ORDER BY project_count DESC
LIMIT 1;