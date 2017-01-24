class CreateObservacions < ActiveRecord::Migration
  def change
    create_table :observacions do |t|
      t.text :descripcion

      t.timestamps null: false
    end
    
    create_table :observacions_citas, id: false do |t|
      t.belongs_to :observacion, index: true
      t.belongs_to :cita, index: true
    end
    
    create_table :observacions_pacientes, id: false do |t|
      t.belongs_to :observacion, index: true
      t.belongs_to :paciente, index: true
    end
  end
end
