json.extract! enfermedad, :id, :codigo, :descripcion, :created_at, :updated_at
json.url enfermedad_url(enfermedad, format: :json)