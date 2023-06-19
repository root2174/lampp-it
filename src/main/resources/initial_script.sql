CREATE TABLE IF NOT EXISTS PUBLIC.user_system (
  id BIGINT NOT NULL,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO public.user_system(id,name,email,username,password)
    SELECT 1,'admin', 'admin@admin.com', 'admin','$2a$10$JHBxLrpvVTOdjF7gwlE8p.S6yqmdnHlzY9lwiqm68IZgtuxj9KV1q'
    WHERE
    NOT EXISTS (
        SELECT 1 FROM public.user_system where id = 1
    ) ;