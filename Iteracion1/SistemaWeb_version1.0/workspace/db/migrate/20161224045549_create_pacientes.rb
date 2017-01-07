class CreatePacientes < ActiveRecord::Migration
  def change
    create_table :pacientes do |t|
      t.string :cedula
      t.string :nombre
      t.string :apellido1
      t.string :apellido2
      t.date :fechaNacimiento
      t.string :nacionalidad
      t.string :genero
      t.date :fechaFallecimiento

      t.timestamps null: false
    end
  end
end
