import React, {Component} from 'react';
import {
  StyleSheet,
  View
} from 'react-native';
import {Calendar} from 'react-native-calendars';

export default class CalendarsScreen extends Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.onDayPress = this.onDayPress.bind(this);
  }

  render() {
    return (
      <View style={styles.container}>
        <Calendar
          onDayPress={this.onDayPress}
          style={styles.calendar}
          hideExtraDays
          markedDates={{[this.state.selected]: {selected: true, disableTouchEvent: true, selectedDotColor: 'orange'}}}
        />
      </View>
    );
  }

  onDayPress(day) {
    this.setState({
      selected: day.dateString,
    });
    //SAVE DATA SOMEWHERE, GO NEXT PAGE
    this.props.setDateFromParent(day.dateString)
  }
}

const styles = StyleSheet.create({
  calendar: {
    paddingTop: 20,
    height: 350
  },
  text: {
    textAlign: 'center',
    borderColor: '#bbb',
  },
  container: {
    flex: 1,
  }
});
