-- 
set @jd = 5412;
delete from POST_TAG where POST_ID in (select id from post where GOVERNMENTORGAN_ID = @jd);
delete from post_like where POST_ID in (select id from post where GOVERNMENTORGAN_ID = @jd);
delete from POST_COMMENT_REPLIES where POST_COMMENT_ID in (select id from POST_COMMENT where POST_ID in (select id from post where GOVERNMENTORGAN_ID = @jd));
delete from POST_COMMENT where POST_ID in (select id from post where GOVERNMENTORGAN_ID = @jd);
delete from POST where GOVERNMENTORGAN_ID = @jd;
delete from GOVERNMENT_ORGAN where id = @jd;
SELECT * FROM GOVERNMENT_ORGAN where PARENTGOVORG_ID  = 5392;
SELECT * FROM GOVERNMENT_ORGAN where PARENTGOVORG_ID  = @jd;
