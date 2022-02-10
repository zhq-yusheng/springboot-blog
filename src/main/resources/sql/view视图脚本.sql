CREATE VIEW  QUERY_BLOG_view AS 
SELECT 
bid,
username,
imgUrl AS txUrl,
blog.`btid` AS btid,
blogType,
title,
titlehtml,
titleimg,
body,
bodyHtml,
(SELECT COUNT(*) FROM popularity WHERE popularity.`bid` = blog.`bid`) popularity,
blog.`createDatetime`
FROM
blog
LEFT JOIN USER ON blog.uid = user.uid
LEFT JOIN blog_type ON blog.btid = blog_type.`btid`
WHERE blog.`deleted` = 0;



create view user_Blog_Data_View as
select 
u.uid as uid,
u.`createUpDatetime` createUpDatetime,
(SELECT COUNT(1) FROM popularity p WHERE p.bid IN (SELECT bid FROM blog WHERE blog.`uid` = u.uid)) as popularity,
ifnull((select COUNT(1) from blog  b  WHERE b.uid = u.uid and deleted = 0 ), 0) as  countBlog
from user u;

CREATE VIEW Site_information_view AS
SELECT 
(SELECT COUNT(1) FROM blog WHERE deleted = 0) AS blogCount, -- 博客总数
(SELECT COUNT(1) FROM blog_type WHERE del = 0) AS blogTypeCount, -- 博客类型总数
(SELECT COUNT(1) FROM USER ) AS upCount, -- 用户数量
(SELECT COUNT(1) FROM leaving_messing) leavingCount, -- 留言总数
(SELECT COUNT(1) FROM popularity ) AS popCount -- 点击量总数
FROM DUAL

CREATE VIEW myblog_view AS 
SELECT
bid,
title,
(SELECT blogtype FROM blog_type bt WHERE bt.btid = b.btid) AS blogType,
(SELECT COUNT(1) FROM popularity p WHERE p.bid = b.bid) AS popularity,
titleimg,
body,
createDatetime
FROM 
blog b WHERE b.deleted = 0


CREATE VIEW blog_count_view AS 
SELECT 
title,
(SELECT username FROM USER WHERE user.uid = b.`uid`) AS author,
(SELECT COUNT(1) FROM popularity p WHERE p.bid = b.bid) AS  popularity
FROM
 blog b WHERE b.`deleted` = 0

 CREATE VIEW author_count_view AS 
SELECT 
username,
(SELECT COUNT(1) FROM blog WHERE blog.uid = u.`uid`) AS bcounts,
(SELECT COUNT(1) FROM popularity p WHERE p.`bid` IN (SELECT bid FROM blog WHERE blog.uid = u.`uid`)) AS popularity 
FROM
 USER u WHERE isup = 1