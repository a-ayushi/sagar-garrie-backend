-- V1__init_schema.sql
CREATE TABLE IF NOT EXISTS menu_items (
  id BIGSERIAL PRIMARY KEY,
  category VARCHAR(100),
  name VARCHAR(255) NOT NULL,
  description TEXT,
  price NUMERIC(10,2) NOT NULL,
  veg BOOLEAN DEFAULT false,
  image_url VARCHAR(500),
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS bookings (
  id BIGSERIAL PRIMARY KEY,
  customer_name VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  booking_date DATE NOT NULL,
  booking_time TIME NOT NULL,
  pax INT NOT NULL,
  notes TEXT,
  status VARCHAR(50) DEFAULT 'pending',
  pay_now BOOLEAN DEFAULT false,
  payment_id BIGINT,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_bookings_date_time ON bookings (booking_date, booking_time);
CREATE INDEX IF NOT EXISTS idx_bookings_status ON bookings (status);

CREATE TABLE IF NOT EXISTS payments (
  id BIGSERIAL PRIMARY KEY,
  booking_id BIGINT,
  gateway VARCHAR(50),
  gateway_order_id VARCHAR(255),
  amount NUMERIC(10,2),
  currency VARCHAR(10),
  status VARCHAR(50),
  response_payload JSONB,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- seed some menu items
INSERT INTO menu_items (category, name, description, price, veg) VALUES
('Starters','Paneer Tikka','Grilled cottage cheese with spices',220.00,true),
('Main Course','Butter Chicken','Classic buttery chicken curry',320.00,false),
('Beverages','Masala Chai','Traditional spiced tea',40.00,true)
ON CONFLICT DO NOTHING;
