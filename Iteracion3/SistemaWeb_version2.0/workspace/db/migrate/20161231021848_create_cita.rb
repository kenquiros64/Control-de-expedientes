class CreateCita < ActiveRecord::Migration
  def change
    create_table :cita do |t|
      t.references :medico, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
